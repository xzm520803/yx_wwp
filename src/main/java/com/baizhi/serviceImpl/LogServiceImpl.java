package com.baizhi.serviceImpl;
import com.baizhi.dao.LogMapper;
import com.baizhi.entity.Log;
import com.baizhi.entity.LogExample;
import com.baizhi.service.LogService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("logService")
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logDAO;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Log> findLog(Integer page, Integer rows) {
        //相当于是一个条件，没有条件对所有数据进行分页
        LogExample example = new LogExample();
        //分页查询： 参数：忽略几条,获取几条数据
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);

        //查询
        List<Log> logs = logDAO.selectByExampleAndRowBounds(example, rowBounds);

        return logs;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer totalCounts() {
        Log log = new Log();
        Integer integer = logDAO.selectCount(log);
        return integer;
    }

}
