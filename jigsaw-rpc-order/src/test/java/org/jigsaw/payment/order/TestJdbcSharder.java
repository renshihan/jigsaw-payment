package org.jigsaw.payment.order;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shamphone@gmail.com
 * @version 1.0.0
 * @date 2017年8月11日
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(classes= TestConfiguration.class, webEnvironment=WebEnvironment.NONE)
public class TestJdbcSharder {
	@Resource(name = "jdbcSharder")
	private JdbcSharder sharder;

	/**
	 * 验证按照算法计算出来的ID属于同一个数据库；
	 */
	@Test
	public void TestGetTemplateByUserIdEquals() {
		Assert.assertEquals(sharder.getTemplateByUserId(1081l),sharder.getTemplateByUserId(1063l));
		Assert.assertEquals(sharder.getTemplateByUserId(1081l),sharder.getTemplateByUserId(1049l));
		Assert.assertNotEquals(sharder.getTemplateByUserId(1081l),sharder.getTemplateByUserId(1079l));
	}
	/**
	 * 验证按照算法计算出来的ID属于同一个表；
	 */
	@Test
	public void TestGetTableByUserIdEquals() {
		Assert.assertEquals(sharder.getTableByUserId(1081l),sharder.getTableByUserId(1061l));
		Assert.assertEquals(sharder.getTableByUserId(1081l),sharder.getTableByUserId(1041l));
		Assert.assertNotEquals(sharder.getTableByUserId(1081l),sharder.getTableByUserId(1079l));
	}
}
