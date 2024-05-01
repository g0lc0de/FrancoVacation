package adapter;

import domain.entity.Entity;

public interface UiMapper {

    void mapFromDomainEntityToUiResource(Entity domainEntity);
}
