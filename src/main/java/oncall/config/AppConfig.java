package oncall.config;

import oncall.controller.OnCallController;
import oncall.model.Parser;
import oncall.model.SafetyPolicy;
import oncall.view.InputView;
import oncall.view.OutputView;

public class AppConfig {
    public OnCallController onCallController() {
        return new OnCallController(inputView(), outputView(), safetyPolicy(), parser());
    }
    public InputView inputView() {
        return new InputView();
    }
    public OutputView outputView() {
        return new OutputView();
    }
    public SafetyPolicy safetyPolicy() {
        return new SafetyPolicy();
    }
    public Parser parser() {
        return new Parser();
    }
}
