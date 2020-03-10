from Duck        import Duck
from MallardDuck import MallardDuck
from WildTurkey  import WildTurkey

class TurkeyAdapter(Duck, WildTurkey):

    def quack(self):
        self.gobble()

    def fly(self):
        for x in range(5):
            WildTurkey.fly(self)

if __name__ == '__main__':
    birds = []
    birds.append(MallardDuck())
    birds.append(TurkeyAdapter())
    birds.append(WildTurkey())

    x = 0
    for bird in birds:
        print "-" * 50
        print "Bird %d: %s" % (x, bird.__class__.__name__)
        print "-" * 50
        print
        bird.quack()
        bird.fly()
        print
        x += 1
