package com.unictive.unictiveUserApplication.controller.view

import com.unictive.unictiveUserApplication.domain.dto.request.ReqLoginDto
import com.unictive.unictiveUserApplication.domain.dto.request.ReqRegisterDto
import com.unictive.unictiveUserApplication.service.AuthService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/v1/cms")
class CmsViewController(
    private val authService: AuthService
) {

    @GetMapping("/register")
    fun showRegisterForm(model: Model): String {
        model.addAttribute("request", ReqRegisterDto())
        return "registerPage"
    }

    @GetMapping("/login")
    fun showLoginPage(model: Model): String {
        model.addAttribute("request", ReqLoginDto())
        return "loginPage"
    }

    @GetMapping("/home")
    fun showHomePage(): String {
        return "userHome"
    }

    @GetMapping("/addUser")
    fun showAddUserPage(): String {
        return "addUserPage"
    }

    @GetMapping("/editUser")
    fun showEditUserPage(
        @RequestParam("idUser") idUser: Long,
        model: Model
    ): String {
        model.addAttribute("idUser", idUser)
        return "editUserPage"
    }
}

