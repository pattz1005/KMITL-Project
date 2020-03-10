def profit(d, p, k):
    if k == 0:
        return p[0]
    if k < 0:
        return 0
    
    next_shop = k - 1 
    while d[next_shop] + 500 > d[k]:
        if next_shop < 0:
            return 0
        next_shop -= 1
    
    return max(p[k] + profit(d, p, next_shop), profit(d, p, k - 1))
        
def main():
    d = [100,  300,   600,  700,  900, 1300]
    p = [1500, 2000, 1300, 1700, 2000, 1600]

    print(profit(d, p, len(d) - 1))

main()
