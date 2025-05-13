package com.campusflow.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.campusflow.mapper.ClazzsMapper;
import com.campusflow.mapper.StudentMapper;
import com.campusflow.service.impl.ClazzsServiceImpl;

import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
class ClazzsServiceImplTest {

    @Mock
    private ClazzsMapper clazzsMapper;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private ClazzsServiceImpl clazzsService;

    @Test
    void deleteShouldMoveStudentsBeforeDeletingClazz() {
        clazzsService.delete(5);

        InOrder order = inOrder(studentMapper, clazzsMapper);
        order.verify(studentMapper).clearClazzId(5);
        order.verify(clazzsMapper).delete(5);
    }
}
