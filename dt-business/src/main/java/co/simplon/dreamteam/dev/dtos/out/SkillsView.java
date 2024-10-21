package co.simplon.dreamteam.dev.dtos.out;

public record SkillsView(
        Long id,
        String name,
        boolean isTechnical,
        Long level,
        String levelName) {
}
