import android.content.Intent;
import android.os.IBinder;


public class Service extends android.app.Service {

    public IBinder onBind(Intent intent) {
        return null;
    }




    public void onCreate(){
        super.onCreate();
    }
    public void onStart(Intent intent, int startId){
        System.out.println("El servicio a Comenzado");
        this.stopSelf();
    }
    public void onDestroy(){
        super.onDestroy();
        System.out.println("El servicio a Terminado");
    }
}
