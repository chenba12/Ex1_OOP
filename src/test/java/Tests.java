import org.example.observer.ConcreteMember;
import org.example.observer.GroupAdmin;
import org.example.observer.Member;
import org.example.observer.UndoableStringBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(value = BlockJUnit4ClassRunner.class)
public class Tests {
    private UndoableStringBuilder usb;
    private GroupAdmin groupAdmin;
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);

    @Before
    public void init() {

        logger.info(JvmUtilities::jvmInfo);
        groupAdmin = new GroupAdmin();
        usb = groupAdmin.getUsb();
        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin));
        ConcreteMember cm1 = new ConcreteMember(groupAdmin.getUsb(), "chen");
        groupAdmin.register(cm1);
        groupAdmin.register(new ConcreteMember(groupAdmin.getUsb(), "or"));
        groupAdmin.register(new ConcreteMember(groupAdmin.getUsb(), "amit"));
        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin));
        groupAdmin.append("Hello");
        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin));
        logger.info(() -> JvmUtilities.objectTotalSize(groupAdmin, cm1));
        logger.info(() -> "Concrete member info: " + JvmUtilities.objectFootprint(cm1));
        logger.info(() -> "Group admin info: " + JvmUtilities.objectFootprint(groupAdmin));

    }


    @Test
    public void testRegister() {
        groupAdmin.register(null);
        List<Member> list = new ArrayList<>();
        list.add(new ConcreteMember(groupAdmin.getUsb(), "chen"));
        list.add(new ConcreteMember(groupAdmin.getUsb(), "or"));
        list.add(new ConcreteMember(groupAdmin.getUsb(), "amit"));
        assertEquals(list, groupAdmin.getMemberList());
    }

    @Test
    public void testUnregister() {
        ConcreteMember cm1 = (ConcreteMember) groupAdmin.getMemberList().get(0);
        groupAdmin.unregister(cm1);

        List<Member> list = new ArrayList<>();
        list.add(new ConcreteMember(groupAdmin.getUsb(), "or"));
        list.add(new ConcreteMember(groupAdmin.getUsb(), "amit"));
        assertEquals(list, groupAdmin.getMemberList());
    }

    @Test
    public void testUpdate() {
        assertEquals("Hello", usb.toString());
        ConcreteMember concreteMember = (ConcreteMember) groupAdmin.getMemberList().get(0);
        assertEquals(usb.toString(), concreteMember.getUsb().toString());
        usb.append(null);
        assertEquals(usb.toString(), concreteMember.getUsb().toString());
    }

}
