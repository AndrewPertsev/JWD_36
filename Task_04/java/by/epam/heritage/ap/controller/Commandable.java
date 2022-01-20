package by.epam.heritage.ap.controller;


import by.epam.heritage.ap.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Commandable {
    void execute(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException;
}
