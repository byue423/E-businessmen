
package ws.sdkj.com.bettomtab;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Window;
import android.widget.Toast;

/**
 *
 */
public abstract class BaseAty extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	/**
	 * 处理与子线程交互的Handler
	 * @param msg
	 */
	public abstract void handlerMessage(Message msg);

	//动态改变状态栏颜色
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public void setStatusColor(int color){
		getWindow().setStatusBarColor(color);
	}

	/** 获取主题色 */
	public int getColorPrimary() {
		TypedValue typedValue = new TypedValue();
		getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
		return typedValue.data;
	}

	/** 获取深主题色 */
	public int getDarkColorPrimary() {
		TypedValue typedValue = new TypedValue();
		getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
		return typedValue.data;
	}
	public void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	private ProgressDialog dialog;

	public void showLoading() {
		if (dialog != null && dialog.isShowing()) return;
		dialog = new ProgressDialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setMessage("正在拼命记载中...");
		dialog.show();
	}



	public void dismissLoading() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	public void showSuccessDialog(String msg) {
		if (dialog != null && dialog.isShowing()) return;
		dialog = new ProgressDialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setMessage(msg);
		dialog.show();
	}



	public void dismissSuccessDialog() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}
}
