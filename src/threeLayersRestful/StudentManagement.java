package threeLayersRestful;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class StudentManagement extends JFrame{

	/**
	 * @param args
	 */
	
	private QlsvBll mqlsvBll;
	private JButton addBtn;
	private JButton editBtn;
	private JButton delBtn;
	
	private DefaultTableModel dtm;
	private JTable table;
	int selectedRow;
	
	Vector<String> vTitle = new Vector<>();
	Vector<Vector<String>> vData = new Vector<>();
	List<Sinhvien> Students;
	
	public StudentManagement() {
		// TODO Auto-generated constructor stub
		mqlsvBll = new QlsvBll();
		
		
		dtm = new DefaultTableModel(vData, vTitle); 
		table = new JTable();
		getTitleColumnTable();
		reload(mqlsvBll.show());
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JTable table = (JTable) e.getSource();
				selectedRow = table.getSelectedRow();
			}
		});
		JScrollPane scroll = new JScrollPane(table);
		add(scroll);
		
		
		JPanel BottomPane = new JPanel();add(BottomPane,"South");
		addBtn = new JButton("Thêm");
		editBtn = new JButton("Sửa");//editBtn.setEnabled(false);
		delBtn = new JButton("Xóa");//delBtn.setEnabled(false);
		BottomPane.add(addBtn);
		BottomPane.add(editBtn);
		BottomPane.add(delBtn);
		addEvent();
		
		setSize(600, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	
	private void getTitleColumnTable(){
		vTitle = mqlsvBll.getNameCols();
	}
	
	public void reload(List<Sinhvien> show) {//List to vData
		// TODO Auto-generated method stub
		vData.clear();
		Students = show;
		Iterator<Sinhvien> it = Students.iterator();
		while(it.hasNext()){
			Vector<String> vt = it.next().getVectorData();
			vData.add(vt);
		}
		
		dtm = new DefaultTableModel(vData, vTitle);
		table.setModel(dtm);
		
	}



	private void addEvent(){
		ActionListener acForBtn = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton btn = (JButton) e.getSource();
				if(btn==addBtn){new InsertForm(StudentManagement.this, mqlsvBll, "Insert", null);}
				if(btn==editBtn){
					Sinhvien sv = Students.get(selectedRow);
					new InsertForm(StudentManagement.this, mqlsvBll, "Edit", sv);
				}
				if(btn==delBtn) {
					Sinhvien sv = Students.get(selectedRow);
					int mssv = sv.getMssv();
					mqlsvBll.delSv(mssv);
					reload(mqlsvBll.show());}
			}
		};
		
		addBtn.addActionListener(acForBtn);
		editBtn.addActionListener(acForBtn);
		delBtn.addActionListener(acForBtn);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StudentManagement();
	}

}

class InsertForm extends JFrame implements ActionListener{
	
	private DefaultComboBoxModel<String> mBoxModel;
	private JComboBox<String> CBLop;
	List<Lop> lopS;
	
	private JButton ok;
	private JButton cancel;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfDoB;
	private JTextField tfDtb;
	
	StudentManagement sm;
	QlsvBll bll;
	
	public InsertForm(StudentManagement sm,QlsvBll bll,String title, Sinhvien sv) {
		// TODO Auto-generated constructor stub
		super(title);
		this.sm = sm;//hold mainframe refs to InsertForm
		this.bll = bll;//save mem & access DB
		
		JPanel panel = new JPanel(new GridLayout(7, 2));
		JLabel lbId = new JLabel("MSSV");
		tfID = new JTextField();tfID.setEditable(false);
		JLabel lbName = new JLabel("Ten");
		tfName = new JTextField();
		JLabel lbDoB = new JLabel("Ngày sinh");
		tfDoB = new JTextField();
		JLabel lbDtb = new JLabel("DTB");
		tfDtb = new JTextField();
		JLabel lbKhoa = new JLabel("Lop");
		JLabel error1 = new JLabel();
		JLabel error2 = new JLabel();
		loadLop();
		ok = new JButton(title);ok.addActionListener(this);
		cancel = new JButton("Cancel");cancel.addActionListener(this);
		panel.add(lbId);panel.add(tfID);panel.add(lbName);panel.add(tfName);
		panel.add(lbDoB);panel.add(tfDoB);panel.add(lbDtb);panel.add(tfDtb);
		panel.add(lbKhoa);panel.add(CBLop);
		panel.add(error1);panel.add(error2);panel.add(ok);panel.add(cancel);
		add(panel);
		
		
		
		if(this.getTitle().equals("Edit")){
			tfID.setText(String.valueOf(sv.getMssv()));
			tfName.setText(sv.getHoTen());
			tfDoB.setText(sv.getDoB());
			tfDtb.setText(String.valueOf(sv.getAve()));
			CBLop.setSelectedIndex(sv.getLop().getIdLop()-1);//in DB begin 1,cb begin 0
			
			
		}
		
		setSize(300, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		
		
		
		
	}

	private void loadLop() {
		// TODO Auto-generated method stub
		lopS = bll.getAllLop();
		String[] tenLopS = new String[lopS.size()];
		for (int i = 0; i < lopS.size(); i++) {
			tenLopS[i] = lopS.get(i).getTenLop();
		}
		mBoxModel = new DefaultComboBoxModel<>(tenLopS);
		CBLop = new JComboBox<>(mBoxModel);	//start begin 0
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getSource();
		if(btn == ok){
			if(this.getTitle().equals("Insert")) insert();
			else edit();
			
			sm.reload(bll.show());
			this.dispose();
		}
		if(btn == cancel){
			this.dispose();
		}
	}
	
	private void insert(){
		Sinhvien sv = new Sinhvien();
		sv.setHoTen(tfName.getText());
		sv.setDoB(tfDoB.getText());
		if(tfDtb.getText().equals("")) sv.setAve(0); else sv.setAve(Float.parseFloat(tfDtb.getText()));
		sv.setLop(lopS.get(CBLop.getSelectedIndex()));
		bll.addSv(sv);
	}
	
	private void edit(){
		Sinhvien sv = new Sinhvien();
		sv.setMssv(Integer.parseInt(tfID.getText()));
		sv.setHoTen(tfName.getText());
		sv.setDoB(tfDoB.getText());
		if(tfDtb.getText().equals("")) sv.setAve(0); else sv.setAve(Float.parseFloat(tfDtb.getText()));
		sv.setLop(lopS.get(CBLop.getSelectedIndex()));
		bll.editSv(sv);
	}
	
}