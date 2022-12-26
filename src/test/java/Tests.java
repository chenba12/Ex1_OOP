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
        groupAdmin = new GroupAdmin();
        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin));
        usb = groupAdmin.getUsb();
        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin, usb));
        groupAdmin.register(new ConcreteMember(groupAdmin.getUsb(), "chen"));
        groupAdmin.register(new ConcreteMember(groupAdmin.getUsb(), "or"));
        groupAdmin.register(new ConcreteMember(groupAdmin.getUsb(), "amit"));
        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin.getMemberList()));
        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    public void testRegister() {
        groupAdmin.register(null);
        List<Member> list = new ArrayList<>();
        list.add(new ConcreteMember(groupAdmin.getUsb(), "chen"));
        logger.info(() -> JvmUtilities.objectFootprint(list));
        list.add(new ConcreteMember(groupAdmin.getUsb(), "or"));
        logger.info(() -> JvmUtilities.objectFootprint(list));
        list.add(new ConcreteMember(groupAdmin.getUsb(), "amit"));
        logger.info(() -> JvmUtilities.objectFootprint(list));
        assertEquals(list, groupAdmin.getMemberList());
        logger.info(() -> JvmUtilities.objectFootprint(list));
        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    public void testUnregister() {
        ConcreteMember cm1 = (ConcreteMember) groupAdmin.getMemberList().get(0);
        groupAdmin.unregister(cm1);

        List<Member> list = new ArrayList<>();
        list.add(new ConcreteMember(groupAdmin.getUsb(), "or"));
        list.add(new ConcreteMember(groupAdmin.getUsb(), "amit"));
        assertEquals(list, groupAdmin.getMemberList());
        logger.info(() -> JvmUtilities.objectFootprint(cm1,groupAdmin.getMemberList()));
        logger.info(() -> JvmUtilities.objectFootprint(list));
        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    public void testUpdate() {
        groupAdmin.append("Hello");
        assertEquals("Hello", usb.toString());
        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin.getUsb()));
        ConcreteMember concreteMember = (ConcreteMember) groupAdmin.getMemberList().get(0);
        assertEquals(usb.toString(), concreteMember.getUsb().toString());
        usb.append(null);
        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin.getUsb()));
        assertEquals(usb.toString(), concreteMember.getUsb().toString());
        logger.info(() -> JvmUtilities.jvmInfo());
    }

}
