package com.github.ubunfu.mclogbot.enums;

public enum DeathEnum {

    ARROWS_SHOT_BY_ARROW                ("was shot by"),
    CACTUS_PRICKED                      ("was pricked to death"),
    CACTUS_WALKED_INTO                  ("walked into a cactus whilst trying to escape"),
    DROWNING_DROWNED                    ("drowned"),
    ELYTRA_EXPERIENCED_KINETIC_ENERGY   ("experienced kinetic energy"),
    EXPLOSIONS_BLEW_UP_SELF             ("blew up"),
    EXPLOSIONS_BLOWN_UP                 ("was blown up by"),
    EXPLOSIONS_INTENTIONAL_DESIGN       ("was killed by [Intentional Game Design]"),
    FALLING_HIT_GROUND                  ("hit the ground too hard"),
    FALLING_FELL_FROM_HIGH_PLACE        ("fell from a high place"),
    FALLING_FELL_OFF                    ("fell off"),
    FALLING_FELL_WHILE                  ("fell while"),
    FALLING_BLOCKS                      ("was squashed by a falling"),
    FIRE_WENT_UP                        ("went up in flames"),
    FIRE_WALKED_INTO_FIRE               ("walked into fire whilst fighting"),
    FIRE_BURNED                         ("burned to death"),
    FIRE_CRISPED                        ("was burnt to a crisp whilst fighting"),
    FIREWORKS                           ("went off with a bang"),
    LAVA                                ("tried to swim in lava"),
    LIGHTNING                           ("was struck by lightning"),
    MAGMA_FLOOR_WAS_LAVA                ("discovered the floor was lava"),
    MAGMA_DANGER_ZONE                   ("walked into danger zone due to"),
    MAGIC                               ("was killed by"),
    PLAYERS_MOBS_SLAIN                  ("was slain by"),
    PLAYERS_MOBS_FIREBALLED             ("was fireballed by"),
    PLAYERS_MOBS_STUNG                  ("was stung to death"),
    PLAYERS_MOBS_SHOT                   ("was shot by a"),
    STARVING                            ("starved to death"),
    SUFFOCATION_SUFFOCATED_IN_WALL      ("suffocated in a wall"),
    SUFFOCATION_SQUISHED                ("was squished too much"),
    SUFFOCATION_SQUASHED                ("was squashed by"),
    SWEET_BERRY_BUSH                    ("was poked to death by a sweet berry bush"),
    THORNS                              ("trying to hurt"),
    TRIDENT                             ("was impaled by"),
    VOID_FELL                           ("fell out of the world"),
    VOID_SUICIDE                        ("didn't want to live in the same world as"),
    WITHER                              ("withered away");


    private final String deathMessage;

    DeathEnum(String deathMessage) {
        this.deathMessage = deathMessage;
    }

    public String deathMessage() {
        return deathMessage;
    }
}
