package BookManager.utils;

import BookManager.model.User;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;


public class toolUtil {
    public static boolean isEmpty(String str){
        if(str!=null&&!"".equals(str.trim())){
            return false;
        }
        return true;
    }
    public static boolean isNotEmpty(String str){
        if(str!=null&&!"".equals(str.trim())){
            return true;
        }
        return false;
    }
    public static Long getTime(){
        long Time=System.currentTimeMillis();
        return Time;
    }
    public static String getDateByTime(Long time){
        SimpleDateFormat format=new SimpleDateFormat("yyy--MM-dd HH:mm:ss");
        String string=format.format(new Date(time));
        return string;
    }
    public static User getUser(HttpSession session){
    User user=(User)session.getAttribute("user");
    return user;
    }
    public static void setUser(HttpSession session,User user){
        session.setAttribute("user",user);
    }
}
