// Generated code from Butter Knife. Do not modify!
package hr.fer.opp.onedayjob.Activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import hr.fer.opp.onedayjob.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VerificationActivity_ViewBinding implements Unbinder {
  private VerificationActivity target;

  @UiThread
  public VerificationActivity_ViewBinding(VerificationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VerificationActivity_ViewBinding(VerificationActivity target, View source) {
    this.target = target;

    target.verificationCode = Utils.findRequiredViewAsType(source, R.id.verification_code, "field 'verificationCode'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VerificationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.verificationCode = null;
  }
}
