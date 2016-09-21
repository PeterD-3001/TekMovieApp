package be.vdab.spring.mvc;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cerseilannister on 20/09/16.
 */

public interface ActorRepository extends JpaRepository<Actor, Integer>
{
}

