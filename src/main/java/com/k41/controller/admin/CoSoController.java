package com.k41.controller.admin;

import com.k41.config.TaiKhoan;
import com.k41.constants.PageActive;
import com.k41.constants.PageConstant;
import com.k41.entity.CoSo;
import com.k41.entity.NguoiHienMau;
import com.k41.excel.ImportDTO;
import com.k41.excel.ImportService;
import com.k41.service.CoSoService;
import com.k41.service.NguoiHienMauService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/admin/coso")
public class CoSoController {
    @Autowired
    private CoSoService coSoService;

    @Autowired
    private NguoiHienMauService nguoiHienMauService;

    @Autowired
    private TaiKhoan taiKhoan;

    @Autowired
    private ImportService importService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String coSoPage(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        loadTen(model);
        if (ObjectUtils.isEmpty(page)) page = 1;
        Pageable paging = PageRequest.of(page - 1, PageConstant.PAGE_SIZE, Sort.by("id").descending());
        Page<CoSo> pageable = coSoService.findPage(paging);
        List<CoSo> coSos = pageable.getContent();
        model.addAttribute("coSos", coSos);
        model.addAttribute("totalPage", pageable.getTotalPages());
        return "/admin/CoSo/CoSo";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String themCoSo(@RequestParam(value = "page", defaultValue = "1") int page, Model model, HttpServletRequest request) {
        loadTen(model);
        String tenCoSo = request.getParameter("tenCoSo");
        CoSo coSo = new CoSo();
        coSo.setTenCoSo(tenCoSo);
        coSoService.save(coSo);
        if (ObjectUtils.isEmpty(page)) page = 1;
        Pageable paging = PageRequest.of(page - 1, PageConstant.PAGE_SIZE, Sort.by("id").descending());
        Page<CoSo> pageable = coSoService.findPage(paging);
        List<CoSo> coSos = pageable.getContent();
        model.addAttribute("message", "Thêm Thành công");
        model.addAttribute("alert", "success");
        model.addAttribute("coSos", coSos);
        model.addAttribute("totalPage", pageable.getTotalPages());
        return "/admin/CoSo/CoSo";
    }

    @RequestMapping(value = {"/doiquyen"}, method = RequestMethod.GET)
    public String capNhatQuyen(@RequestParam(value = "id", required = true) Long id, Model model) {
        loadTen(model);
        CoSo coSo = coSoService.updateEnable(id);
        return "redirect:/admin/coso";
    }

    @RequestMapping(value = {"/danhsach"}, method = RequestMethod.GET)
    public String danhSachHienMauCoSo(@RequestParam(value = "id", required = true) Long id, @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        loadTen(model);
        if (ObjectUtils.isEmpty(page)) page = 1;
        Pageable paging = PageRequest.of(page - 1, PageConstant.PAGE_SIZE, Sort.by("lastModifiedDate").descending());
        Page<NguoiHienMau> pageable = nguoiHienMauService.findPageByCoSoId(paging, id);
        List<NguoiHienMau> nguoiHienMaus = pageable.getContent();
        CoSo coSo = coSoService.findById(id);
        model.addAttribute("coso", coSo);
        model.addAttribute("nguoiHienMaus", nguoiHienMaus);
        model.addAttribute("totalPage", pageable.getTotalPages());
        return "/admin/NguoiHienMau/DanhSachNguoiHienMau";
    }

    @RequestMapping(value = {"/danhsach"}, method = RequestMethod.POST)
    public String importDanhSachHienMauCoSo(@RequestParam(value = "id", required = true) Long id,
                                            Model model, HttpServletRequest request, MultipartFile file) throws Exception {
        try {
            List<ImportDTO> importDTOS = importService.importExcelFile(file);
            nguoiHienMauService.saveByImportDTO(importDTOS, id);
        } catch (Exception exception) {
            model.addAttribute("message", "File không đúng định dạng");
            model.addAttribute("alert", "danger");

        }
        loadTen(model);
        Pageable paging = PageRequest.of(0, PageConstant.PAGE_SIZE, Sort.by("lastModifiedDate").descending());
        Page<NguoiHienMau> pageable = nguoiHienMauService.findPageByCoSoId(paging, id);
        List<NguoiHienMau> nguoiHienMaus = pageable.getContent();
        CoSo coSo = coSoService.findById(id);
        model.addAttribute("coso", coSo);
        model.addAttribute("nguoiHienMaus", nguoiHienMaus);
        model.addAttribute("totalPage", pageable.getTotalPages());
        return "/admin/NguoiHienMau/DanhSachNguoiHienMau";
    }

    private void loadTen(Model model) {
        model.addAttribute(PageActive.activecoso, PageActive.active);
        model.addAttribute(PageActive.tennguoidung, taiKhoan.getTaiKhoanDangNhap().getHoTen());
    }
}
