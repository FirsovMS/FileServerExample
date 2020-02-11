import org.junit.*;

import com.company.Services.DdService.DBService;
import com.company.Services.DdService.dataSets.ModulesDataSet;

public class DBServiceTest {
    private DBService dbService;

    @Before
    public void setUp() {
        dbService = new DBService();
    }

    @Test
    @Ignore
    public void testAdd() throws Exception {
        long ident = dbService.addModuleInfo(new ModulesDataSet(-1, "test.jar", 999 ));
    }

    @Test
    @Ignore
    public void testGetting() throws Exception {
        ModulesDataSet dummySet = new ModulesDataSet(-2, "testing.jar", 999 );
        dbService.addModuleInfo(dummySet);
        // get set from database
        ModulesDataSet reqSet = dbService.getModuleInfo(dummySet.getName());

        //check values
        Assert.assertEquals(dummySet.getId(), reqSet.getId());
        Assert.assertEquals(dummySet.getName(), reqSet.getName());
        Assert.assertEquals(dummySet.getCrc(), reqSet.getCrc());
    }

    @After
    public void setDown(){
        dbService = null;
    }

}
