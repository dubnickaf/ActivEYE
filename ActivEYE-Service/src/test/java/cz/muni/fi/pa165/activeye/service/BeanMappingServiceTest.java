package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.config.ServiceConfiguration;
import cz.muni.fi.pa165.activeye.dto.GroupDTO;
import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   25-10-2016
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BeanMappingService beanMappingService;

    private Set<User> users = new HashSet<User>();
    private List<Group> groups = new ArrayList<Group>();

    @BeforeMethod
    public void createTestData() {

        User u1 = new User();
        User u2 = new User();

        Group group = new Group();

        u1.setId(0l);
        u1.setName("Carl Pilkington");
        u1.setEmailAddress("carl.pilkington@headlikeanorange.com");

        u2.setId(1l);
        u2.setName("Llyonel Monde");
        u2.setEmailAddress("llyonel.monde@victoriasscret.com");

        users.add(u1);
        users.add(u2);

        group.setName("Atleti");
        group.setCreatorsUserId(u1.getId());
        group.setUsers(users);

        groups.add(group);

    }

    @Test
    public void shouldMapInnerUsers() {
        List<GroupDTO> groupDTOs = beanMappingService.mapTo(groups, GroupDTO.class);
        Assert.assertEquals(groupDTOs.get(0).getUsers().size(), 2);
    }

}
