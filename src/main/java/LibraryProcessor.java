import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;

/**
 * Created by wangdong on 2018/5/1.
 *
 * 爬取图书馆粉丝地理位置的分析
 *
 * https://weibo.com/p/1001062108661792/follow?relate=fans&page=1
 * 需要模拟登陆，sele啥的把cookie截取下来
 */


public class LibraryProcessor  implements PageProcessor {




    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setTimeOut(1000000);

    public Site getSite()
    {
        for (Cookie cookie:cookies)
        {
            site.addCookie(cookie.getName().toString(),cookie.getValue().toString());
            return site.addHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");

        }

        return site;
    }

    private Set<Cookie> cookies;


    public void login()

    {


        WebDriver driver = new ChromeDriver();

        driver.get("https://weibo.com/login.php?url=http%3A%2F%2Fpay.vip.weibo.com%2F");

        driver.findElement(By.id("loginname")).clear();


        driver.findElement(By.id("loginname")).sendKeys("18010614539");

        driver.findElement(By.name("password")).clear();
        //在*******填你密码
        driver.findElement(By.name("password")).sendKeys("550965989wd++l");

        //模拟点击登录按钮
        driver.findElement(By.cssSelector(".W_btn_a.btn_32px")).click();

        //获取cookie信息
        cookies = driver.manage().getCookies();
        driver.close();
    }



    public void process(Page page){

        System.out.println(page.getHtml().toString());



    }

    public static void main(String[] args) {
        String url="https://weibo.com/p/1001062108661792/follow?relate=fans&page=2#Pl_Official_HisRelation__47";


        LibraryProcessor libraryProcessor=new LibraryProcessor();

        libraryProcessor.login();



        Spider.create(libraryProcessor).addUrl(url).thread(1).run();

    }






}

