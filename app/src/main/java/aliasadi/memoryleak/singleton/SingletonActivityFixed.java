package aliasadi.memoryleak.singleton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import aliasadi.memoryleak.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class SingletonActivityFixed extends Activity {

    SingletonManagerFixed singletonFixed;

    public static void start(Context context) {
        Intent starter = new Intent(context, SingletonActivityFixed.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        /**Leak **/
        //If you referenced context directly and that reference happened to be an Activity or View or any context that not application context
        //then you would have created a memory leak.

        /**Fix **/
        //You will not create a leak since you are only keeping a reference
        //to the application context by calling getApplicationContext() on the construct.
        singletonFixed = SingletonManagerFixed.getInstance(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // FIX2 : destroy it onDestroy.
        singletonFixed.destroy();
    }
}