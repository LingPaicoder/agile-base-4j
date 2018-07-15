package com.lpcoder.agile.base.forj.check;

import com.lpcoder.agile.base.forj.check.ruler.Ruler;
import com.lpcoder.agile.base.forj.check.ruler.detail.object.ObjNotNullRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.string.StrIdCardRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.string.StrNotEmptyRuler;
import com.lpcoder.agile.base.forj.check.ruler.detail.string.StrNotNullRuler;
import com.lpcoder.agile.base.forj.check.ruler.summary.IntRuler;
import com.lpcoder.agile.base.forj.check.ruler.summary.ObjRuler;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import lombok.Data;

import static com.lpcoder.agile.base.forj.check.ruler.summary.StrRuler.*;

public class CheckUtilTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * 常规操作
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testCommon() {
        String trueIdcard = "130802198108204219";
        CheckUtil.check(trueIdcard, "身份证号",
                new StrNotNullRuler(),
                new StrNotEmptyRuler(),
                new StrIdCardRuler());

        thrown.expect(CheckException.class);
        thrown.expectMessage("code=-11007, desc=身份证号必须符合身份证格式");

        String falseIdcard = "130802198108204210";
        CheckUtil.check(falseIdcard, "身份证号",
                new StrNotNullRuler(),
                new StrNotEmptyRuler(),
                new StrIdCardRuler());
    }

    /**
     * Ruler工厂类
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testFactory() {
        String trueIdcard = "130802198108204219";
        CheckUtil.check(trueIdcard, "身份证号", notNull(), notEmpty(), idCard());

        thrown.expect(CheckException.class);
        thrown.expectMessage("code=-11007, desc=身份证号必须符合身份证格式");

        String falseIdcard = "130802198108204210";
        CheckUtil.check(falseIdcard, "身份证号", notNull(), notEmpty(), idCard());
    }

    /**
     * 捕获校验失败异常
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testCatch() {
        try {
            String falseIdcard = "130802198108204210";
            CheckUtil.check(falseIdcard, "身份证号",
                    notNull(),
                    notEmpty(),
                    idCard());
        } catch (CheckException e) {
            Assert.assertEquals(-11007L, e.getCode());
            Assert.assertEquals("身份证号必须符合身份证格式", e.getDesc());
            Assert.assertEquals("code=-11007, desc=身份证号必须符合身份证格式", e.getMessage());
        }
    }

    /**
     * 自定义错误编号和错误描述(支持在desc中对norm进行格式化)
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testUserDefinedFailCodeAndDesc() {
        String specialName = "乔伊·亚历山大·比基·卡利斯勒·达夫·埃利奥特·福克斯·伊维鲁莫";
        final String NAME_DESC = "姓名";
        final long NAME_TOO_LONG_CODE = -2L;
        final int lteNorm = 10;
        // 注意此处%d的用法
        final String NAME_TOO_LONG_DESC = "长度超过限制,允许的最大长度:%d";

        thrown.expect(CheckException.class);
        thrown.expectMessage("code=-2, desc=姓名长度超过限制,允许的最大长度:10");

        CheckUtil.check(specialName, NAME_DESC,
                notNull(),
                notEmpty(),
                lengthGte(2),
                lengthLte(lteNorm, NAME_TOO_LONG_CODE, NAME_TOO_LONG_DESC));
    }

    /**
     * 链式调用
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testChainingCall() {
        String trueIdcard = "130802198108204219";
        String specialName = "乔伊·亚历山大·比基·卡利斯勒·达夫·埃利奥特·福克斯·伊维鲁莫";

        thrown.expect(CheckException.class);
        thrown.expectMessage("code=-11005, desc=姓名的长度必须小于或等于10");
        CheckUtil.check(trueIdcard, "身份证号",
                notNull(),
                notEmpty(),
                idCard());
        CheckUtil.check(specialName, "姓名",
                notNull(),
                notEmpty(),
                lengthGte(2),
                lengthLte(10));

        thrown.expect(CheckException.class);
        thrown.expectMessage("code=-11005, desc=姓名的长度必须小于或等于10");
        CheckUtil
                .check(trueIdcard, "身份证号",
                        notNull(),
                        notEmpty(),
                        idCard())
                .check(specialName, "姓名",
                        notNull(),
                        notEmpty(),
                        lengthGte(2),
                        lengthLte(10));
    }

    /**
     * 已校验结果可依赖性/后置校验对象求值中断功能
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testNull() {
        // 注意：并没有抛出NPE
        thrown.expect(CheckException.class);
        thrown.expectMessage("code=-10000, desc=商家不能为Null");
        Custom custom = null;
        CheckUtil.check(custom, "商家", new ObjNotNullRuler())
                .check(custom.getCustomId(), "商家Id", new StrNotNullRuler());

        // 补充：除了通过异常中断,还可以利用FunctionInterface的缓求值特性来实现该功能
        // 但只能通过lambda表达式来生成Supplier对象,方法引用仍会抛出NPE
        // 详见：https://www.jianshu.com/p/d3e69bd192c7
    }

    /**
     * 规则整合功能(Ruler多合一)
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testRulerMerge() {
        String specialName = "乔伊·亚历山大·比基·卡利斯勒·达夫·埃利奥特·福克斯·伊维鲁莫";
        Ruler<String> nameRuler = Ruler.ofAll(
                notNull(),
                notEmpty(),
                lengthGte(2),
                lengthLte(10));

        thrown.expect(CheckException.class);
        thrown.expectMessage("code=-11005, desc=姓名的长度必须小于或等于10");
        CheckUtil.check(specialName, "姓名", nameRuler);
    }

    /**
     * 规则的“或”逻辑
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testOr() {
        Ruler<String> nameRuler =
                empty().or(lengthGte(2), lengthLte(10));

        String specialName = "";
        CheckUtil.check(specialName, "姓名", nameRuler);

        specialName = "张三";
        CheckUtil.check(specialName, "姓名", nameRuler);

        thrown.expect(CheckException.class);
        thrown.expectMessage("code=-11005, desc=姓名的长度必须小于或等于10");
        specialName = "乔伊·亚历山大·比基·卡利斯勒·达夫·埃利奥特·福克斯·伊维鲁莫";
        CheckUtil.check(specialName, "姓名", nameRuler);

    }

    /**
     * 为实体类不同操作封装个性化Ruler
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testEntity() {
        Ruler<Custom> customAddRuler = custom -> CheckUtil.check(custom, "商家", ObjRuler.notNull())
                .check(custom.getCustomId(), "商家Id", notEmpty())
                .check(custom.getName(), "商家姓名", notEmpty())
                .check(custom.getAge(), "商家年龄", IntRuler.lte(60));

        Custom custom = new Custom();
        custom.setCustomId("123");
        custom.setName("张三");
        custom.setAge(80);

        thrown.expect(CheckException.class);
        thrown.expectMessage("code=-18005, desc=商家年龄必须小于或等于60");
        CheckUtil.check(custom, "", customAddRuler);

    }

    @Data
    static class Custom {
        private String customId;
        private String name;
        private Integer age;
    }
}