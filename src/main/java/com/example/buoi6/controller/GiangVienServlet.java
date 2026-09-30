package com.example.buoi6.controller;

import com.example.buoi6.entity.GiangVien;
import com.example.buoi6.respository.GiangVienRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GiangVienServlet", value = {
        "/giang-vien/hien-thi-tat-ca",
        "/giang-vien/detail",
        "/giang-vien/remove",
        "/giang-vien/view-update",
        "/giang-vien/update",
        "/giang-vien/tim-kiem",
        "/giang-vien/add"
})
public class GiangVienServlet extends HttpServlet {

    private GiangVienRepository repo = new GiangVienRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();

        if (uri.contains("hien-thi-tat-ca")) {
            this.hienThi(request, response);
        } else if (uri.contains("detail")) {
            this.detail(request, response);
        } else if (uri.contains("remove")) {
            this.remove(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdate(request, response);
        } else {
            this.hienThi(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();

        if (uri.contains("tim-kiem")) {
            this.timKiem(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        } else if (uri.contains("add")) {
            this.add(request, response);
        }
    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<GiangVien> list = repo.getAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/view/giang-vien.jsp").forward(request, response);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        GiangVien gv = repo.getOne(id);
        request.setAttribute("gvDetail", gv);
        request.setAttribute("list", repo.getAll());
        request.getRequestDispatcher("/view/giang-vien.jsp").forward(request, response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        repo.delete(id);
        response.sendRedirect(request.getContextPath() + "/giang-vien/hien-thi-tat-ca");
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        GiangVien gv = repo.getOne(id);
        request.setAttribute("gv", gv);
        request.getRequestDispatcher("/view/update-giang-vien.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        GiangVien gv = GiangVien.builder()
                .id(Long.parseLong(request.getParameter("id")))
                .mssv(request.getParameter("mssv"))
                .ten(request.getParameter("ten"))
                .tuoi(Long.parseLong(request.getParameter("tuoi")))
                .gioiTinh("1".equals(request.getParameter("gioiTinh")))
                .queQuan(request.getParameter("queQuan"))
                .build();
        repo.update(gv);
        response.sendRedirect(request.getContextPath() + "/giang-vien/hien-thi-tat-ca");
    }

    private void timKiem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ten = request.getParameter("ten");
        String minStr = request.getParameter("tuoiMin");
        String maxStr = request.getParameter("tuoiMax");

        Long tuoiMin = (minStr == null || minStr.trim().isEmpty()) ? null : Long.parseLong(minStr);
        Long tuoiMax = (maxStr == null || maxStr.trim().isEmpty()) ? null : Long.parseLong(maxStr);

        List<GiangVien> list = repo.search(ten, tuoiMin, tuoiMax);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/view/giang-vien.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mssv = request.getParameter("mssv");
        String ten = request.getParameter("ten");
        String tuoiStr = request.getParameter("tuoi");
        String queQuan = request.getParameter("queQuan");
        String gioiTinhStr = request.getParameter("gioiTinh");

        if (mssv == null || mssv.trim().isEmpty()
                || ten == null || ten.trim().isEmpty()
                || tuoiStr == null || tuoiStr.trim().isEmpty()
                || queQuan == null || queQuan.trim().isEmpty()
                || gioiTinhStr == null) {
            request.setAttribute("error", "Không được để trống!");
            request.setAttribute("list", repo.getAll());
            request.getRequestDispatcher("/view/giang-vien.jsp").forward(request, response);
            return;
        }

        GiangVien gv = GiangVien.builder()
                .mssv(mssv)
                .ten(ten)
                .tuoi(Long.parseLong(tuoiStr))
                .gioiTinh("1".equals(gioiTinhStr))
                .queQuan(queQuan)
                .build();
        repo.add(gv);
        response.sendRedirect(request.getContextPath() + "/giang-vien/hien-thi-tat-ca");
    }
}