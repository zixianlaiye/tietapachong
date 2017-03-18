package entity;

/**
 * Created by wangdong on 2017/3/5.
 */
public class Price {

    private  String supplierName;
    private  String provinceName;
    private  String freight_rate;

    public String getSupplierName() {
        return supplierName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public String getFreight_rate() {
        return freight_rate;
    }


    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setFreight_rate(String freight_rate) {
        this.freight_rate = freight_rate;
    }

    @Override
    public String toString() {
        return supplierName + '|' + provinceName + '|' + freight_rate ;
    }
}
