package org.sterl.education.angular;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Assuming everything prefixed with <code>/ui/</code> and not a <code>.</code> in the URL is a HTML 5 route.
 */
@Controller
@RequestMapping("/ui")
public class AngularUiController {
    @GetMapping("{[path:[^\\.]*}")
    String index() {
        return "forward:/ui/index.html";
    }
}
