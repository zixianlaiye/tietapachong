import com.jayway.jsonpath.JsonPath;
import com.sun.org.apache.xpath.internal.operations.Or;
import entity.Order;
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
 * Created by wangdong on 2017/2/12.
 */
public class OrderPageProcessor implements PageProcessor {


    public static HashMap<String,String> typeList=new HashMap<String, String>();
    static{

       typeList.put("21","室外一体化机柜");
        typeList.put("22","室外一体化机柜(内置电源)");
        typeList.put("32","组合开关电源");
        typeList.put("33","嵌入开关电源");
        typeList.put("34","壁挂开关电源");
        typeList.put("54","动环监控设备FSU");


    }


    //对url的判断
    public static Pattern urlPattern=Pattern.compile("(?<=supplycode=).*(?=&pageIndex)");

    //对产品分类代码的截取
    public static Pattern typePattern=Pattern.compile("(?<=prd_big_type_code=).*(?=&supplycode=)");

    public static int [] province= {130001, 530001, 370001, 510001, 620001, 430001, 500001, 520001, 410001, 320001, 360001, 340001, 440001, 140001, 230001, 450001, 610001, 420001, 330001, 650001, 150001, 460001, 350001, 210001, 120001, 630001, 640001, 220001, 110001, 540001, 310001
    };

    public static int[] type={21,22,32,33,34,54};






    List<String> url =new ArrayList<String>();


    public   Pattern Province =Pattern.compile("");



    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setTimeOut(1000000);



    public void process(Page page) {

        //主页
        if (page.getUrl().toString() == "https://www.toryburch.com/stores-viewal")
        {
            List<String> urlList = new ArrayList<String>();


            StringBuilder url = new StringBuilder();
            for (int i = 0; i < province.length; i++) {


                for (int j = 0; j < type.length; j++) {
                    //获取省份列表

                    url.append("http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.getSupplyInfo.biz.ext?ProvinceOrgCode=" + province[i] + "&prd_big_type_code=" + type[j] + "&supplycode=null&pageIndex=0&pageSize=1000");
                    urlList.add(url.toString());
                    url.delete(0,url.length());


                }
            }

            for (String a : urlList) {

                page.addTargetRequest(a);
            }


        }


        //分省页
        if (page.getUrl().toString().startsWith("http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.getSupplyInfo.biz.ext?ProvinceOrgCode"))

        {
            String address = page.getUrl().toString();
            //对address进行判断，看是分公司列表还是其他。

            String supplycode = "";
            Matcher addressMatcher = urlPattern.matcher(address);
            if (addressMatcher.find()) {
                supplycode = addressMatcher.group();

            }


            //如果是公司列表


            List<Selectable> supplyList;

            try {
                supplyList = page.getJson().jsonPath("$.supplyInfo[*]").nodes();
            } catch (Exception e) {
                supplyList = new ArrayList<Selectable>();
            }


            String supplyCode = "";
            String supplyUrl = "";
            if (supplyList.size() > 0) {


                for (Selectable a : supplyList) {

                    supplyCode = JsonPath.read(a.toString(), "$.SupplyCode").toString();
                    supplyUrl = page.getUrl().replace("null", supplyCode).toString();
                    supplyUrl = supplyUrl.replace("getSupplyInfo", "showSupplyOrder");


                    page.addTargetRequest(supplyUrl);

                    supplyCode = "";
                    supplyUrl = "";
                }


            }
        }
        //若为公司详情表页

        if(page.getUrl().toString().startsWith("http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.showSupplyOrder.biz.ext?ProvinceOrgCode=")){



            String type="";
            Matcher typeMatcher=typePattern.matcher(page.getUrl().toString());
            if(typeMatcher.find())
            {
                type=typeMatcher.group();

            }


            String status=typeList.get(type).toString();




              String supplyname="";
            String orgname="";

             String  provinceorgname="";
           String  ordercode="";
            String  orderdate="";
             String  taxamount="";
             String materialcode="";
             String materialname="";



            List<Selectable> orderList=page.getJson().jsonPath("$.supplyOrder[*]").nodes();
            List<Order> orders=new ArrayList<Order>();

            for(Selectable a:orderList)
            {

                supplyname=JsonPath.read(a.toString(),"$.supplyname").toString();
                orgname=JsonPath.read(a.toString(),"$.orgname").toString();
                provinceorgname=JsonPath.read(a.toString(),"$.provinceorgname").toString();

                ordercode=JsonPath.read(a.toString(),"$.ordercode").toString();

                orderdate=JsonPath.read(a.toString(),"$.orderdate").toString();
                taxamount=JsonPath.read(a.toString(),"$.taxamount").toString();
                materialcode=JsonPath.read(a.toString(),"$.materialcode").toString();
                materialname=JsonPath.read(a.toString(),"$.materialname").toString();


                Order order=new Order();

                order.setMaterialcode(materialcode);
                order.setMaterialname(materialname);
                order.setOrdercode(ordercode);
                order.setOrgname(orgname);
                order.setProvinceorgname(provinceorgname);
                order.setSupplyname(supplyname);
                order.setTaxamount(taxamount);
                order.setOrderdate(orderdate);

                orders.add(order);




            }




            //对数据的写入：
            synchronized (this)
            {

                try{
//                    File writename = new File();
//                    BufferedWriter out = new BufferedWriter(new FileWriter(writename));
//
//                    out.write();
//                    out.flush();
                    String fileName="/home/dongdong/a.txt";

                    FileWriter writer = new FileWriter(fileName, true);

                    //若要分类，就判断一下url所属分类，写入不同的txt文件就行了
                    for(Order a:orders)
                    { writer.write(status+","+a.toCsv()+"\r\n");

                    }

                    writer.close();
                    System.out.println(page.getUrl().toString());


                }
                catch (Exception e)
                {

                    e.printStackTrace();
                }


            }





            //对每个公司每笔交易的截取
//               List<Selectable> companyList=page.getJson().jsonPath("$.supplyOrder[*]").nodes();


//
//                String supplyname;
//                String orgname;
//
//                String  provinceorgname;
//                String  ordercode;
//                String  orderdate;
//               String  taxamount;
//               String materialcode;
//               String materialname;
//
//               for(Selectable a:companyList)
//               {
//                   supplyname=JsonPath.read(a.toString(),"$.supplyname");
//                   orgname=JsonPath.read(a.toString(),"$.orgname");
//                   provinceorgname=JsonPath.read(a.toString(),"$.provinceorgname");
//                   ordercode=JsonPath.read(a.toString(),"$.ordercode");
//                   orderdate=JsonPath.read(a.toString(),"$. orderdate");
//                   taxamount=JsonPath.read(a.toString(),"$.taxamount");
//                   materialcode=JsonPath.read(a.toString(),"$.materialcode");
//                   materialname=JsonPath.read(a.toString(),"$.materialname");
//                   supplyname=JsonPath.read(a.toString(),"$.supplyname");
//
//
//
//
//                   Order order=new Order();
//                   order.setMaterialcode();
//
//
//

        }

    }












    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        String url="https://www.toryburch.com/stores-viewal";


        Spider.create(new OrderPageProcessor()).addUrl(url).thread(10).run();
    }



}
