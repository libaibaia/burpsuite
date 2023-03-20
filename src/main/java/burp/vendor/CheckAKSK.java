package burp.vendor;

import burp.common.Base;
import burp.common.IAction;
import burp.Main;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.scanner.audit.issues.AuditIssue;
import burp.api.montoya.scanner.audit.issues.AuditIssueConfidence;
import burp.api.montoya.scanner.audit.issues.AuditIssueSeverity;
import burp.ui.ListsModule;
import burp.ui.UI;

import java.util.ArrayList;
import java.util.List;

public class CheckAKSK implements Base {
    private final HttpRequestResponse baseRequestResponse;
    private final List<AuditIssue> list = new ArrayList<>();
    public static final String[] keyWord = new String[]{"ACCESSKEYID","ACCESSKEYSECRET",
            "ACCESS_KEY_ID","ACCESS_KEY_SECRET",
            "AccessKey","AccessSecret","Secret",
            "AccessKeySecret","SECRET_KEY",
            "ACCESS_KEY",};
    @Override
    public List<AuditIssue> checkVul() {
        IAction[] action = {
                this::checkAKSK
        };
        for (IAction iAction : action) {
            iAction.execute();
        }
        return list;
    }

    /***
     * 检查响应结果，是否存在key泄露
     */
    private void checkAKSK(){
        for (String s : keyWord) {
//             || baseRequestResponse.response().bodyToString().toLowerCase().contains(s.toLowerCase())
            if (baseRequestResponse.response().body().countMatches(s) >= 1){
                AuditIssue auditIssue = AuditIssue.auditIssue("ACCESSKEYID LEAK","THERE MAY BE AN ACCESSKEYID LEAK","",baseRequestResponse.url(), AuditIssueSeverity.HIGH
                        , AuditIssueConfidence.FIRM,"","",AuditIssueSeverity.HIGH,baseRequestResponse);
                list.add(auditIssue);
                String res = getStringPoint(s,baseRequestResponse.response().bodyToString());
                UI.updateUIData(new ListsModule(baseRequestResponse),res);
            }
        }
    }

    private String getStringPoint(String s,String targetString) {
        int length = targetString.length();
        String res = null;
        int i = targetString.indexOf(s);
        if ((i + 50) >= length){
            res = targetString.substring(i);
        }else {
            res = targetString.substring(i,i + 50);
        }
        return res;
    }

    public CheckAKSK(HttpRequestResponse httpRequestResponse){
        this.baseRequestResponse = httpRequestResponse;
    }
}
