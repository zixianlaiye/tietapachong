/**
 * Created by wangdong on 2017/10/27.
 * 从写入好的TXT中再写入excel
 */


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sun.tools.corba.se.idl.constExpr.Or;
import entity.Order;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

public class XlsxChange {

    public static void main(String args[])
    {


        try{

            String pathName="/Users/wangdong/order/xab";

            File filename=new File(pathName);

            InputStreamReader reader=new InputStreamReader(new FileInputStream(filename));
            BufferedReader bufferedReader=new BufferedReader(reader);

            List<Order> orders=new ArrayList<Order>(1100000);






            String line="";
            while(line!=null)
            {
                line=bufferedReader.readLine();
                Order order=new Order();
                String [] info =line.split(",");

            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
