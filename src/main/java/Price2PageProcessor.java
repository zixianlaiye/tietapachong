import com.jayway.jsonpath.JsonPath;
import entity.Order;
import entity.Price;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangdong on 2017/3/5.
 *
 * 对每个公司路途费报价的截取。
 */
public class Price2PageProcessor implements PageProcessor{

    public static HashMap<String,String> typeList=new HashMap<String, String>();
    static{

        typeList.put("21","室外一体化机柜");
        typeList.put("22","室外一体化机柜(内置电源)");
        typeList.put("32","组合开关电源");
        typeList.put("33","嵌入开关电源");
        typeList.put("34","壁挂开关电源");
        typeList.put("54","动环监控设备FSU");


    }


    private Pattern  typePattern =Pattern.compile("(?<=&prodBcatg1=).*(?=&pageIndex)");


    public static String [] url=new String[5] ;
    static {

        url[0]="http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.getTowerQuote.biz.ext?prdBigType=21&pageIndex=0&pageSize=10000";
        url[1]="http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.getTowerQuote.biz.ext?prdBigType=22&pageIndex=0&pageSize=10000";
        url[2]="http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.getTowerQuote.biz.ext?prdBigType=32&pageIndex=0&pageSize=10000";
        url[3]="http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.getTowerQuote.biz.ext?prdBigType=33&pageIndex=0&pageSize=10000";
        url[4]="http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.getTowerQuote.biz.ext?prdBigType=34&pageIndex=0&pageSize=10000";
//        url[5]="http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.getTowerQuote.biz.ext?prdBigType=54&pageIndex=0&pageSize=10000";

    }


    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setTimeOut(1000000);







    public void process(Page page) {

        if(page.getUrl().toString().startsWith("https://www.toryburch.com/stores-viewal"))
        {
            for(String a:url)
            {
                page.addTargetRequest(a);

            }


        }


        if(page.getUrl().toString().startsWith("http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.getTowerQuote.biz.ext?prdBigType="))
        {

            List<Selectable>  supplyList=page.getJson().jsonPath("$.quote[*]").nodes();

            //利用hashmap去重，每个公司只记一次。

            HashMap<String,Integer> companyList=new HashMap<String, Integer>();


            for(Selectable b:supplyList)
            {

                if(companyList.get(JsonPath.read(b.toString(),"$.supplierId").toString())==null)
                {
                    companyList.put(JsonPath.read(b.toString(),"$.supplierId").toString(),1);
                    page.addTargetRequest("http://www.tower.com.cn/default/quote/productQuoteInfoShow/cn.chinatowercom.obp.quote.supplierquoteinfobiz.queryOthFreightFeeTab.biz.ext?supplierId="+JsonPath.read(b.toString(),"$.supplierId").toString()+"&prodBcatg1="+JsonPath.read(b.toString(),"$.productId").toString()+"&pageIndex=0&pageSize=100");

                }

            }
        }

        if(page.getUrl().toString().startsWith("http://www.tower.com.cn/default/quote/productQuoteInfoShow/cn.chinatowercom.obp.quote.supplierquoteinfobiz.queryOthFreightFeeTab.biz.ext?supplierId"))
        {

            String type="";
            //截取所属大类信息
            Matcher typeMatcher=typePattern.matcher(page.getUrl().toString());
            if(typeMatcher.find())
            {
                type=typeList.get(typeMatcher.group());
            }



            //截取31个省份的运保费信息
            List<Selectable>  provinceList=page.getJson().jsonPath("$.freightfeelist[*]").nodes();

            List<Price> priceList=new ArrayList<Price>();

            //将数据进出进list

            for(Selectable c:provinceList)
            {

                Price price =new Price();

                price.setSupplierName(JsonPath.read(c.toString(),"$.supplierName").toString());
                price.setFreight_rate(JsonPath.read(c.toString(),"$.freight_rate").toString());
                price.setProvinceName(JsonPath.read(c.toString(),"$.provinceName").toString());
                priceList.add(price);
            }

            //锁，然后进行文本写入操作

            synchronized (this)
            {


                try{

                    String fileName="/home/dongdong/price2.txt";

                    FileWriter writer = new FileWriter(fileName, true);

                    //若要分类，就判断一下url所属分类，写入不同的txt文件就行了
                    for(Price a:priceList)
                    { writer.write(type+"|"+a.toString()+"\r\n");

                    }

                    writer.close();
                    System.out.println(page.getUrl().toString());


                }
                catch (Exception e)
                {

                    e.printStackTrace();
                }
            }




        }






    }

    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        String url="https://www.toryburch.com/stores-viewal";


        Price2PageProcessor price2PageProcessor=new Price2PageProcessor();
        Spider.create(price2PageProcessor).addUrl("https://www.toryburch.com/stores-viewal").thread(10).run();
    }



}
