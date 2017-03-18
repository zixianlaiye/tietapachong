/**
 * Created by wangdong on 2017/2/11.
 */


import com.jayway.jsonpath.JsonPath;
import entity.Company;
import entity.Order;

import entity.Price;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PricePageProcessor implements PageProcessor{

    public static String [] url=new String[6] ;
    static {

        url[0]="http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.getTowerQuote.biz.ext?prdBigType=21&pageIndex=0&pageSize=10000";
        url[1]="http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.getTowerQuote.biz.ext?prdBigType=22&pageIndex=0&pageSize=10000";
        url[2]="http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.getTowerQuote.biz.ext?prdBigType=32&pageIndex=0&pageSize=10000";
        url[3]="http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.getTowerQuote.biz.ext?prdBigType=33&pageIndex=0&pageSize=10000";
        url[4]="http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.getTowerQuote.biz.ext?prdBigType=34&pageIndex=0&pageSize=10000";
        url[5]="http://www.tower.com.cn/default/main/skins/menubar/cn.chinatowercom.obp.portal.getProtal.getTowerQuote.biz.ext?prdBigType=54&pageIndex=0&pageSize=10000";

    }



    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setTimeOut(1000000);



public void process(Page page) {


if(page.getUrl().toString()=="https://www.toryburch.com/stores-viewal")
{
    for(String a:url)
    {
        page.addTargetRequest(a);
    }
}

if(page.getUrl().toString().startsWith("http://www.tower.com.cn/default/main/skins/me"))
{
    List<Selectable>  priceList=page.getJson().jsonPath("$.quote[*]").nodes();

    List<Company> companyList=new ArrayList<Company>();

    for(Selectable b:priceList)
    {
        Company company=new Company();

        company.setSupplierName(JsonPath.read(b.toString(),"$.supplierName").toString());
        company.setbType(JsonPath.read(b.toString(),"$.bType").toString());
        company.setpMinType(JsonPath.read(b.toString(),"$.pMinType").toString());
        company.setProductPrice(JsonPath.read(b.toString(),"$.productPrice").toString());

        companyList.add(company);

    }


    //写入操作
    synchronized (this)
    {
        try{

            String fileName="/home/dongdong/price1.txt";

            FileWriter writer = new FileWriter(fileName, true);

            //若要分类，就判断一下url所属分类，写入不同的txt文件就行了
            for(Company a:companyList)
            { writer.write(a.toString()+"\r\n");

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

        PricePageProcessor pricePageProcessor=new PricePageProcessor();
        Spider.create(pricePageProcessor).addUrl("https://www.toryburch.com/stores-viewal").thread(10).run();


    }




}
