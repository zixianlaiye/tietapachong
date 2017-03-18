package entity;

/**
 * Created by wangdong on 2017/3/6.
 *
 * 各公司具体报价表
 */
public class Company {

    private String supplierName;
    private String bType;
    private String pMinType;
    private String productPrice;

    public String getSupplierName() {
        return supplierName;
    }

    public String getbType() {
        return bType;
    }

    public String getpMinType() {
        return pMinType;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setbType(String bType) {
        this.bType = bType;
    }

    public void setpMinType(String pMinType) {
        this.pMinType = pMinType;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return supplierName + '|' +
                 bType + '|' +
               pMinType + '|' +
                 productPrice ;
    }
}
