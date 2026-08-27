package example.day36_day02_0826.활동6.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import example.day36_day02_0826.활동6.model.dto.ListDto;

public class ListDao extends BaseDao {
    private ListDao(){}
    private static final ListDao instance = new ListDao();
    public static ListDao getInstance(){return instance;}

    // 등록
    public boolean save(ListDto listDto){
        try{
            String sql = "insert into board(number,n) values( ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,listDto.getNumber());
            ps.setInt(2, listDto.getN());
            int result = ps.executeUpdate();
            if(result==1){return true;}
        }catch(Exception e){System.out.println(e);}
        return false;
    }

    // 전체조회
    public ArrayList<ListDto> findAll(){
        ArrayList<ListDto> list = new ArrayList<>();
        try{
            String sql ="select * from board";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ListDto listDto = new ListDto();
                listDto.setNumber(rs.getString("number"));
                listDto.setN(rs.getInt("n"));
                list.add(listDto);
            }
        }catch(Exception e){System.out.println(e);}
        return list;
    }

    // 수정
    public boolean update(ListDto listDto, int n){
        try{
            String sql = "update board set n = ? where number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, n);
            ps.setString(2, listDto.getNumber());
            int result = ps.executeUpdate();
            if(result == 1){return true;}
        }catch(Exception e){System.out.println(e);}
        return false;
    }
    
    // 삭제
    public boolean delete(String number){
        try{
            String sql = "delete from board where number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, number);
            int result = ps.executeUpdate();
            if(result == 1){return true;}
        }catch(Exception e){System.out.println(e);}
        return false;
    }


    
}
