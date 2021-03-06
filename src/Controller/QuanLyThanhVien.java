package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.NguoiDungBean;
import Bo.NguoiDungBo;

/**
 * Servlet implementation class QuanLyThanhVien
 */
@WebServlet("/QuanLyThanhVien")
public class QuanLyThanhVien extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuanLyThanhVien() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    NguoiDungBo nd = new NguoiDungBo();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		try {
			if(request.getParameter("xoa") != null) {
				String taikhoan = request.getParameter("taikhoan");
				int xoa = nd.xoaThanhVien(taikhoan);
				if(xoa != 0) {
					request.setAttribute("kiemtraxoa", "1");
				}
				else {
					request.setAttribute("kiemtraxoa", "0");
					
				}
			}
			if(request.getParameter("key") != null) {
				String key = request.getParameter("key");
				NguoiDungBean n = nd.get1ThanhVien(key);
				ArrayList<NguoiDungBean> lstTV = new ArrayList<NguoiDungBean>();
				if(n != null) {
					lstTV.add(n);
					request.setAttribute("lstTV", lstTV);
					request.setAttribute("kiemtra", "1");
				}
				else {
					request.setAttribute("lstTV", lstTV);
					request.setAttribute("kiemtra", key);
					
				}
				
			}
			
			else {
				request.setAttribute("lstTV", nd.getAllThanhVien());
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		request.getRequestDispatcher("quanlythanhvien.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
