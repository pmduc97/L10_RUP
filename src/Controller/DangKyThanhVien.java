package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.NguoiDungBean;
import Bo.NguoiDungBo;

/**
 * Servlet implementation class DangKyThanhVien
 */
@WebServlet("/DangKyThanhVien")
public class DangKyThanhVien extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangKyThanhVien() {
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
			if(request.getParameter("dangky") != null) {
				String taikhoan = request.getParameter("taikhoan");
				String matkhau = request.getParameter("matkhau");
				String hoten = request.getParameter("hoten") ;
				String ngaysinh = request.getParameter("ngaysinh");
				if(ngaysinh == null)
					ngaysinh = "1990-01-01";
				String cmt = request.getParameter("cmt");
				String dt = request.getParameter("dt");
				String email = request.getParameter("email");
				String diachi = request.getParameter("diachi");
				String gioitinh = request.getParameter("gioitinh");
				boolean gt = gioitinh.equals("1") ? true:false;
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
				Date ns = sim.parse(ngaysinh);
				NguoiDungBean nguoi = nd.get1ThanhVien(taikhoan);
				if(nguoi != null) {
					request.setAttribute("kiemtra", "0");
				}
				else {
					int i = nd.themThanhVien(hoten, ns, cmt, dt, email, diachi, gt, taikhoan, matkhau);
					if(i != 0) {
						request.setAttribute("kiemtra", "1");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("new-register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
