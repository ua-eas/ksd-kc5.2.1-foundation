package org.kuali.kra.test.infrastructure;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.fixture.KimPersonFixture;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.identity.entity.Entity;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.kim.api.identity.type.EntityTypeContactInfo;
import org.kuali.rice.kim.impl.identity.entity.EntityBo;
import org.kuali.rice.kim.impl.identity.principal.PrincipalBo;
import org.kuali.rice.krad.service.BusinessObjectService;

public class PersonAwareTestBase extends KcUnitTestBase {
	
	public Person createPerson(KimPersonFixture kimPerson) {

		// Already exists, or we are on LDAP
		Person person = getPerson(kimPerson);
		if (person != null) {
			return person;
		}

		BusinessObjectService businessObjectService = getService(BusinessObjectService.class);
		Entity entity = getEntity(kimPerson);
		Principal principal = getPrincipal(kimPerson);
		entity.getPrincipals().add(principal);
		EntityBo entityBo = EntityBo.from(entity);
		businessObjectService.save(entityBo);
		
		return getService(PersonService.class).getPerson(kimPerson.getPrincipalId());
	}

	public void deletePerson(KimPersonFixture kimPerson) {

		// Have to delete first, principal.getEntityId() is a FK
		Principal principal = getPrincipal(kimPerson);
		PrincipalBo principalBo = PrincipalBo.from(principal);
		getService(BusinessObjectService.class).delete(principalBo);

		Entity entity = getEntity(kimPerson);
		EntityBo entityBo = EntityBo.from(entity);
		getService(BusinessObjectService.class).delete(entityBo);
	}

	private Entity getEntity(KimPersonFixture kimPerson) {
		Entity.Builder entityBuilder = Entity.Builder.create();
		entityBuilder.setActive(true);
		entityBuilder.setVersionNumber(0L);
		entityBuilder.setId(kimPerson.getEntityId());
		List<EntityTypeContactInfo.Builder> entityTypes = getEntityTypeContactInfoList(kimPerson);
		entityBuilder.setEntityTypes(entityTypes);
		return entityBuilder.build();
	}

	private Principal getPrincipal(KimPersonFixture kimPerson) {
		Principal.Builder principalBuilder = Principal.Builder.create(kimPerson.getPrincipalName());
		principalBuilder.setActive(true);
		principalBuilder.setVersionNumber(0L);
		principalBuilder.setEntityId(kimPerson.getEntityId());
		principalBuilder.setPrincipalId(kimPerson.getPrincipalId());
		return principalBuilder.build();
	}

	private List<EntityTypeContactInfo.Builder> getEntityTypeContactInfoList(KimPersonFixture kimPerson) {
		List<EntityTypeContactInfo.Builder> results = new ArrayList<EntityTypeContactInfo.Builder>();
		EntityTypeContactInfo.Builder builder = EntityTypeContactInfo.Builder.create(kimPerson.getEntityId(), KimConstants.EntityTypes.PERSON);
		builder.setActive(true);
		builder.setVersionNumber(1L);
		results.add(builder);
		return results;
	}

	private Person getPerson(KimPersonFixture kimPerson) {
		return getService(PersonService.class).getPersonByPrincipalName(kimPerson.getPrincipalName());
	}
}
