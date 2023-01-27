import com.xiaozhu.parser.ddl.CreateMaterializedView;
import com.xiaozhu.parser.impl.DemoSqlParserImpl;
import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlDialect;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class CreateMaterializedViewTest {
    @DisplayName("test materialized view creation") // 设置测试项目的
    @Test
    public void test() throws SqlParseException {
        String sql = "CREATE MATERIALIZED VIEW IF NOT EXISTS test.demo.materializationName AS SELECT * FROM dual";

        SqlParser.Config myConfig = SqlParser.config()
                .withQuoting(Quoting.DOUBLE_QUOTE)
                .withQuotedCasing(Casing.UNCHANGED)
                .withParserFactory(DemoSqlParserImpl.FACTORY)
                .withConformance(SqlConformanceEnum.DEFAULT);
        SqlParser parser = SqlParser.create(sql, myConfig);
        SqlNode sqlNode = parser.parseQuery();
        assertTrue(sqlNode instanceof CreateMaterializedView);
        System.out.println(sqlNode);
        System.out.println(sqlNode.toSqlString(SqlDialect.DatabaseProduct.MYSQL.getDialect()));
    }

    @Test
    public void testSubmitWrongOK(){
        String sql = "select ids, name from test where id < 5 and name = 'zhang'";
        SchemaPlus rootSchema = Frameworks.createRootSchema(true);
        final FrameworkConfig config = Frameworks.newConfigBuilder()
                .parserConfig(SqlParser.configBuilder() // 用SqlParser.config代替
                        .setParserFactory(DemoSqlParserImpl.FACTORY)
                        .setCaseSensitive(false)
                        .setQuoting(Quoting.BACK_TICK)
                        .setQuotedCasing(Casing.TO_UPPER)
                        .setUnquotedCasing(Casing.TO_UPPER)
                        .setConformance(SqlConformanceEnum.ORACLE_12)
                        .build()).operatorTable(SqlStdOperatorTable.instance())
                .build();
        SqlParser parser = SqlParser.create(sql, config.getParserConfig());

        try {
            SqlNode sqlNode = parser.parseStmt();
            System.out.println(sqlNode.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
