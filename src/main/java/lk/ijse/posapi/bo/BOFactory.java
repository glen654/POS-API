package lk.ijse.posapi.bo;

import lk.ijse.posapi.bo.impl.CustomerBOImpl;
import lk.ijse.posapi.bo.impl.ItemBOImpl;
import lk.ijse.posapi.bo.impl.OrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBOFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM,ORDER
    }

    public SuperBO getBo(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDER:
                return new OrderBOImpl();
            default:
                return null;
        }
    }
}
