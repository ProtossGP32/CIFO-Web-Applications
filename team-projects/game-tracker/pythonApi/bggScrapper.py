from boardgamegeek.objects.games import BoardGame

from boardgamegeek import BGGClient


def bgg() -> None:
    bgg = BGGClient()
    g = bgg.game("Gloomhaven")
    print(type(g))
    print(g.name)
    print(g.id)
    for n in g.alternative_names:
        print(n)
    print(g.description)
    print(g.expansion)

    print(g.description)
    for n in g.designers:
        print(n)

if __name__ == '__main__':
    bgg()
