/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iselect.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Hiep
 */
@Controller
@RequestMapping("/table")
public class TableController {

    @RequestMapping
    public String get(Model model) {
        return "index";
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newTable(Model model) {
        return "Table.header.new";
    }
    
    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String index(Model model) {
        return "index";
    }


}
