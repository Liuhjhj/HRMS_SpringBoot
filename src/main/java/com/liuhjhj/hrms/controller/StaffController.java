package com.liuhjhj.hrms.controller;

import com.liuhjhj.hrms.dao.DepartmentDao;
import com.liuhjhj.hrms.dao.StaffDao;
import com.liuhjhj.hrms.entity.Department;
import com.liuhjhj.hrms.entity.Staff;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StaffController {

    @Autowired
    StaffDao staffDao;

    @Autowired
    DepartmentDao departmentDao;

    //Get方式是获取页面
    @GetMapping("/staffs")
    public String toListPage(Model model){
        List<Staff> staffs = staffDao.getStaffs();
        List<Department> departments = departmentDao.getDepartments();
        model.addAttribute("staffs",staffs);
        model.addAttribute("departments",departments);
        //员工的StaffList页面的TopBar的按钮类型是Add
        model.addAttribute("buttonAction","add");
        System.out.println("Get staffs:"+staffs);
        return "staff/list";
    }

    @GetMapping("/staff")
    public String toAddPage(Model model){
        List<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        //员工的StaffAdd页面的TopBar的按钮类型是Add
        model.addAttribute("buttonAction","back");
        return "staff/add";
    }

    //Post方式是添加数据
    @PostMapping("/staff")
    public String addStaff(Staff staff){
        staffDao.addStaff(staff);
        System.out.println("Add staff:"+staff);
        return "redirect:/staffs";
    }

    @GetMapping("/staff/{id}")
    //注解是PathVariable，不是Param
    //update和add共用一个页面
    public String toUpdatePage(@PathVariable("id") Integer id, Model model){
        Staff staff = staffDao.getStaffById(id);
        List<Department> departments = departmentDao.getDepartments();
        model.addAttribute("staff",staff);
        System.out.println("to update Staff page");
        model.addAttribute("departments",departments);
        //员工的StaffUpdate页面的TopBar的按钮类型是Back
        model.addAttribute("buttonAction","back");
        return "staff/add";
    }

    //Put方式是更新数据
    @PutMapping("/staff")
    public String updateStaff(Staff staff){
        staffDao.updateStaff(staff);
        System.out.println("update Staff:"+staff);
        return "redirect:/staffs";
    }

    //Delete方式是删除数据
    @DeleteMapping("/staff/{id}")
    public String deleteStaff(@PathVariable("id") Integer id){
        staffDao.deleteStaff(id);
        System.out.println("delete Staff:"+id);
        return "redirect:/staffs";
    }
}