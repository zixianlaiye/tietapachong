/**
 * Created by wangdong on 2017/2/10.
 */
public enum Province {
    shanxi("山西省",1),hebei("河北省",13);

    private String name;
    private int index;



   private Province(String name,int index)
    {
        this.name=name;
        this.index=index;
    }


}
