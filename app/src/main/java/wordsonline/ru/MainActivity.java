package wordsonline.ru;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.xwalk.core.XWalkView;

public class MainActivity extends AppCompatActivity {
    Boolean isInternetPresent = false;
    ConnectionDetector cd;

    private XWalkView xWalkWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Создаем пример класса connection detector:
        cd = new ConnectionDetector(getApplicationContext());
        //Получаем статус Интернет
        isInternetPresent = cd.ConnectingToInternet();

        //Проверяем Интернет статус:
        if (isInternetPresent) {
            xWalkWebView=(XWalkView)findViewById(R.id.xwalkWebView);
//        xWalkWebView.load("http://192.168.0.100:3567/", null);
            xWalkWebView.load("http://words.ligmar.ru:3567/", null);
        }
        else{
//            //Интернет соединения нет
//            //просим пользователя подключить Интернет:
            showAlertDialog(MainActivity.this, "Интернет соединение отсутствует!",
                    "Проверьте настройки подключения.", false);
        }
    }

    /**
     * Функции для отображения простого Alert Dialog
     * @param context - содержимое приложения
     * @param title - название alert dialog
     * @param message - сообщение
     * @param status - успех/неудача (используется для настройки иконки)
     * */
    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        //Настраиваем название Alert Dialog:
        alertDialog.setTitle(title);

        //Настраиваем сообщение:
        alertDialog.setMessage(message);

        //Настраиваем кнопку OK
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        //Отображаем сообщение диалога:
        alertDialog.show();
    }
}
