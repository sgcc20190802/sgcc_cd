package com.sgcc.entity.event;

import com.sgcc.dao.PreBookDao;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.model.PrebookModel;
import com.sgcc.repository.PrebookRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrebookEventEntity {
    @Autowired
    private PrebookRedisRepository prebookRedisRepository;

    public PrebookDTO submitPrebookInfo(PrebookDTO prebookDTO) {
        //dto转dao
        PrebookModel prebookModel = new PrebookModel();
        //TODO
        prebookModel.dto2dao(new ArrayList<PrebookDTO>() {{
            add(prebookDTO);
        }});

        if (prebookRedisRepository.findAllByServiceHallIdAndPrebookDateAndPrebookStartTime(
                prebookDTO.getServiceHallId()
                , prebookDTO.getPrebookDate()
                , prebookDTO.getPrebookStartTime()
        ).size() > 4) {
                return null;
        } else {
            synchronized (this) {
                System.out.println("ENTITY:threadID : " + Thread.currentThread().getId());
                //取出数量
                if (prebookRedisRepository.findAllByServiceHallIdAndPrebookDateAndPrebookStartTime(
                        prebookDTO.getServiceHallId()
                        , prebookDTO.getPrebookDate()
                        , prebookDTO.getPrebookStartTime()
                ).size() > 4) {
                    return null;
                } else {
                    try {
                        prebookRedisRepository.saveAll(prebookModel.getPreBookDaos());

                        //TODO 发MQ 持久化
                        return prebookDTO;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }

                }
            }
        }


    }
}
