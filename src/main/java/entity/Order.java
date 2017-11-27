package entity;

/**
 * Created by wangdong on 2017/2/11.
 */

//订单表实体类
public class Order {
    private  String supplyname;
    private String  orderdate;
    private String orgname;

    private String  provinceorgname;
    private String  ordercode;

    private String  taxamount;
    private String materialcode;
    private String materialname;

    @Override
    public String toString() {
        return "Order{" +
                "supplyname='" + supplyname + '\'' +
                ", orderdate='" + orderdate + '\'' +
                ", orgname='" + orgname + '\'' +
                ", provinceorgname='" + provinceorgname + '\'' +
                ", ordercode='" + ordercode + '\'' +
                ", taxamount='" + taxamount + '\'' +
                ", materialcode='" + materialcode + '\'' +
                ", materialname='" + materialname + '\'' +
                '}';
    }
   

    //以Csv格式输出一条数据
    public String toCsv()
    {
         return supplyname+","+ orderdate+","+provinceorgname+","+ordercode+","+orgname+","+taxamount+","+materialcode+","+materialname;

    }

    public void setSupplyname(String supplyname) {
        this.supplyname = supplyname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public void setProvinceorgname(String provinceorgname) {
        this.provinceorgname = provinceorgname;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public void setTaxamount(String taxamount) {
        this.taxamount = taxamount;
    }

    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    public String getSupplyname() {
        return supplyname;
    }

    public String getOrgname() {
        return orgname;
    }

    public String getProvinceorgname() {
        return provinceorgname;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public String getTaxamount() {
        return taxamount;
    }

    public String getMaterialcode() {
        return materialcode;
    }

    public String getMaterialname() {
        return materialname;
    }
}
