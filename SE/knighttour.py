from visual import*
from pickle import*
class knightplaying:    
     def __init__(self):
          self.createboard()
          self.turn = 0
          self.minturn = None
          self.read()
          while True:
               if self.checkwin() == 0:
                   print("You Win")
                   if self.turn < self.minturn:
                       self.minturn = self.turn
                   self.write()
                   break
               self.valid()
               ev1 = scene.waitfor('mousedown')
               if ev1.pick != None and ev1.pick != self.knight.base and self.check(ev1.pick.pos[0],ev1.pick.pos[2]) == 1:
                    self.removevalid()
                    self.knight.base.pos[0] = ev1.pick.pos[0]
                    self.knight.base.pos[2] = ev1.pick.pos[2]
                    ev1.pick.color = color.blue
                    self.turn += 1
                    
     def write(self):
        try:
            outfile = open("chesspuzzle.dat","wb")
            dump(self.minturn,outfile)
            outfile.close()
        except:
            return 0
        
     def read(self):
        try:
            infile = open("chesspuzzle.dat","rb")
            self.minturn = load(infile)
            infile.close()
        except:
            return 0
        
     def valid(self):
         for j in range(8):
             for i in range(8):
                 if self.check(i,j) == 1 and self.board[j*8+i].base.color != color.blue:
                     self.board[j*8+i].base.color = color.red
                     
     def removevalid(self):
         for j in range(8):
             for i in range(8):
                 if self.board[j*8+i].base.color == color.red:
                     if (i%2 == 0 and j%2 == 0) or (i%2 != 0 and j%2 != 0):
                        self.board[j*8+i].base.color = color.green
                     else:
                        self.board[j*8+i].base.color = color.yellow
                        
     def createboard(self):
          self.board = []
          for j in range(8):
               for i in range(8):
                    if (i%2 == 0 and j%2 == 0) or (i%2 != 0 and j%2 != 0):
                         self.board.append(tile(j,i,color.green))
                    elif (i%2 != 0 and j%2 == 0) or (i%2 == 0 and j%2 != 0):
                         self.board.append(tile(j,i,color.yellow))
          self.knight = horse(0,0)
          self.board[0].base.color = color.blue
          
     def check(self,x,y):
            if 2+self.knight.base.pos[0] == x and  1+self.knight.base.pos[2] == y:
                return 1
            elif 1+self.knight.base.pos[0] == x and  2+self.knight.base.pos[2] == y:
                return 1
            elif -1+self.knight.base.pos[0] == x and  2+self.knight.base.pos[2] == y:
                return 1
            elif -2+self.knight.base.pos[0] == x and  1+self.knight.base.pos[2] == y:
                return 1
            elif -2+self.knight.base.pos[0] == x and  -1+self.knight.base.pos[2] == y:
                return 1
            elif -1+self.knight.base.pos[0] == x and  -2+self.knight.base.pos[2] == y:
                return 1
            elif 1+self.knight.base.pos[0] == x and  -2+self.knight.base.pos[2] == y:
                return 1
            elif 2+self.knight.base.pos[0] == x and  -1+self.knight.base.pos[2] == y:
                return 1
            return 0
        
     def checkwin(self):
          for i in range(64):
              if self.board[i].base.color != color.blue:
                return 1
          return 0
class queenplaying():
    def __init__(self):
        self.createboard()
        self.turn = 0
        while True:
            if self.turn >= 8:
                print "You win "
                break
            elif self.checkboard() == 0:
                print "You Lose "
                break
            ev1 = scene.waitfor('mousedown')
            if ev1.pick != None and ev1.pick.color != color.blue and ev1.pick.color != color.magenta:
                self.valid(int(ev1.pick.pos[2]),int(ev1.pick.pos[0]))
                self.turn += 1                
    def createboard(self):
          self.board = []
          for j in range(8):
               for i in range(8):
                    if (i%2 == 0 and j%2 == 0) or (i%2 != 0 and j%2 != 0):
                         self.board.append(tile(j,i,color.green))
                    elif (i%2 != 0 and j%2 == 0) or (i%2 == 0 and j%2 != 0):
                         self.board.append(tile(j,i,color.yellow))
          self.queens = []
          for i in range(8):
             self.queens.append(None)
    def checkboard(self):
        for i in range(64):
            if self.board[i].base.color != color.blue:
                return 1
        return 0
    def valid(self,y,x):
        self.queens[self.turn] = queen(y,x)
        self.board[y*8+x].base.color = color.blue
        curx = x+1
        cury = y+1
        if curx<8 and cury<8:
                self.board[cury*8+curx].base.color = color.blue
                while curx<7 and cury<7:
                    curx = curx+1
                    cury = cury+1
                    self.board[cury*8+curx].base.color = color.blue
        curx = x-1
        cury = y-1
        if curx>=0 and cury>=0:
                self.board[cury*8+curx].base.color = color.blue
                while curx > 0 and cury > 0:
                    curx = curx-1
                    cury = cury-1
                    self.board[cury*8+curx].base.color = color.blue
        curx = x-1
        cury = y+1
        if curx>=0 and cury<8:
                self.board[cury*8+curx].base.color = color.blue
                while curx > 0 and cury<7:
                    curx = curx-1
                    cury = cury+1
                    self.board[cury*8+curx].base.color = color.blue
        curx = x+1
        cury = y-1
        if curx<8 and cury>=0:
                self.board[cury*8+curx].base.color = color.blue
                while curx<7 and cury> 0:
                    curx = curx+1
                    cury = cury-1
                    self.board[cury*8+curx].base.color = color.blue
        curx = x+1
        cury = y
        if curx<8 and cury<8:
                self.board[cury*8+curx].base.color = color.blue
                while curx<7:
                    curx = curx+1
                    self.board[cury*8+curx].base.color = color.blue
        curx = x-1
        cury = y
        if curx>=0 and cury>=0:
                self.board[cury*8+curx].base.color = color.blue
                while curx > 0:
                    curx = curx-1
                    self.board[cury*8+curx].base.color = color.blue
        curx = x
        cury = y+1
        if curx>=0 and cury<8:
                self.board[cury*8+curx].base.color = color.blue
                while cury<7:
                    cury = cury+1
                    self.board[cury*8+curx].base.color = color.blue
        curx = x
        cury = y-1
        if curx<8 and cury>=0:
                self.board[cury*8+curx].base.color = color.blue
                while cury> 0:
                    cury = cury-1
                    self.board[cury*8+curx].base.color = color.blue
    
class tile:    
    def __init__(self,y,x,tilecolor):
        self.base = box(pos=(x,0,y),length=1,height=0.1,width=1,color=tilecolor)
        
class horse:   
    def __init__(self,y,x):
        self.base = cylinder(pos=(x,0,y),radius=0.4,axis=(0,0.2,0),color=color.white)
        
class queen:
    def __init__(self,y,x):
        self.base = cylinder(pos=(x,0,y),radius=0.4,axis=(0,0.2,0),color=color.magenta)        
scene.center = (3.5,0,3.5)
knightplaying()
#queenplaying()
