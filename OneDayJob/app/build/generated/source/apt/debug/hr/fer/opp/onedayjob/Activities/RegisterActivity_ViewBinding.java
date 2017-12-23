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

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target, View source) {
    this.target = target;

    target.firstName = Utils.findRequiredViewAsType(source, R.id.register_name, "field 'firstName'", EditText.class);
    target.lastName = Utils.findRequiredViewAsType(source, R.id.register_last_name, "field 'lastName'", EditText.class);
    target.pass1 = Utils.findRequiredViewAsType(source, R.id.register_pass, "field 'pass1'", EditText.class);
    target.pass2 = Utils.findRequiredViewAsType(source, R.id.register_pass2, "field 'pass2'", EditText.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.register_phone, "field 'phone'", EditText.class);
    target.mail = Utils.findRequiredViewAsType(source, R.id.register_mail, "field 'mail'", EditText.class);
    target.years = Utils.findRequiredViewAsType(source, R.id.register_years, "field 'years'", EditText.class);
    target.desc = Utils.findRequiredViewAsType(source, R.id.register_summary, "field 'desc'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.firstName = null;
    target.lastName = null;
    target.pass1 = null;
    target.pass2 = null;
    target.phone = null;
    target.mail = null;
    target.years = null;
    target.desc = null;
  }
}
