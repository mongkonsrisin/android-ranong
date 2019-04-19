package th.go.ranong.loveranong;

import android.app.Application;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;


/**
 * Created by Kendo on 5/2/2561.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
