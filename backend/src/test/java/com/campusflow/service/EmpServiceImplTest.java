package com.campusflow.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.campusflow.mapper.EmpExprMapper;
import com.campusflow.mapper.EmpMapper;
import com.campusflow.pojo.Emp;
import com.campusflow.pojo.LoginInfo;
import com.campusflow.pojo.PageResult;
import com.campusflow.service.impl.EmpServiceImpl;
import com.campusflow.utils.JwtUtils;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmpServiceImplTest {

    @Mock
    private EmpMapper empMapper;

    @Mock
    private EmpExprMapper empExprMapper;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmpServiceImpl empService;

    @Test
    void pageShouldUseSameFiltersForCountAndList() {
        LocalDate begin = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 12, 31);
        Emp emp = new Emp();
        emp.setId(1);

        when(empMapper.getCountByCondition("张", 1, begin, end)).thenReturn(1L);
        when(empMapper.pageList(0, 10, "张", 1, begin, end)).thenReturn(List.of(emp));
        when(empExprMapper.getByEmpId(1)).thenReturn(List.of());

        PageResult result = empService.page(1, 10, "张", 1, begin, end);

        assertEquals(1L, result.getTotal());
        assertEquals(1, result.getRows().size());
        verify(empMapper).getCountByCondition("张", 1, begin, end);
    }

    @Test
    void loginShouldReturnJwtWhenPasswordMatches() {
        Emp stored = new Emp();
        stored.setId(7);
        stored.setUsername("admin");
        stored.setName("管理员");
        stored.setPassword("$2a$10$hashed");

        Emp request = new Emp();
        request.setUsername("admin");
        request.setPassword("123456");

        when(empMapper.selectByUsername("admin")).thenReturn(stored);
        when(passwordEncoder.matches("123456", "$2a$10$hashed")).thenReturn(true);
        when(jwtUtils.generateJwt(any())).thenReturn("test-token");

        LoginInfo info = empService.login(request);

        assertNotNull(info);
        assertEquals("test-token", info.getToken());
        assertEquals(7, info.getId());
    }

    @Test
    void loginShouldUpgradeLegacyPlainPassword() {
        Emp stored = new Emp();
        stored.setId(8);
        stored.setUsername("legacy");
        stored.setName("老账号");
        stored.setPassword("123456");

        Emp request = new Emp();
        request.setUsername("legacy");
        request.setPassword("123456");

        when(empMapper.selectByUsername("legacy")).thenReturn(stored);
        when(passwordEncoder.encode("123456")).thenReturn("$2a$10$upgraded");
        when(jwtUtils.generateJwt(any())).thenReturn("token");

        LoginInfo info = empService.login(request);

        assertNotNull(info);
        verify(empMapper).updatePassword(8, "$2a$10$upgraded");
    }

    @Test
    void loginShouldReturnNullWhenPasswordWrong() {
        Emp stored = new Emp();
        stored.setPassword("$2a$10$hashed");

        Emp request = new Emp();
        request.setUsername("admin");
        request.setPassword("wrong");

        when(empMapper.selectByUsername("admin")).thenReturn(stored);
        when(passwordEncoder.matches("wrong", "$2a$10$hashed")).thenReturn(false);

        org.junit.jupiter.api.Assertions.assertNull(empService.login(request));
    }
}
