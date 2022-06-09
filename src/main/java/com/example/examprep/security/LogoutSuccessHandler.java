package com.example.examprep.security;

import com.example.examprep.models.User;
import com.example.examprep.services.StatisticsService;
import com.example.examprep.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private StatisticsService statisticsService;
    private UserService userService;

    @Autowired
    public LogoutSuccessHandler(StatisticsService statisticsService, UserService userService){
        this.statisticsService = statisticsService;
        this.userService = userService;
    }


    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        LocalDateTime loginTime = (LocalDateTime) request.getSession().getAttribute("DateTime");
        User currentUser = userService.findByUsername(authentication.getName());

        statisticsService.updateDuration(loginTime, currentUser);
        request.getSession().invalidate();
        super.onLogoutSuccess(request, response, authentication);
    }
}
