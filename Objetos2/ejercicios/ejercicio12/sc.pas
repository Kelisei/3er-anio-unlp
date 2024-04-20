program x;
Procedure Main;
    var x, y: integer;
    vec: array[1..7] of integer;
    Function B:integer;
        var y:integer;
    begin
        y:=4; x:= y - 2;
        return (x);
    end;
    Procedure D;
        var i, x: integer;
        vec: array[1..7] of integer;
        Procedure A;
            var y:integer;
        begin
            y:=x + 5; vec(i + 2):=
            vec(i + 2) + y;
            x:= x +B; C;
        end;
        Function B:integer;
        begin
            vec(i):= y + 2; i:=i+2;
            vec(i):= vec(1) * i;
            return ( vec(i)-vec(1) );
        end;
    begin
        for x:= 1 to 7 do vec(x):= 1;
        x:=1; i:= 2;
        if y = 7 then A; else C;
        for x:= 1 to 7 do write(vec(x));
    end;
    Procedure C;
        var i, y: integer;
    begin
        i:= 1; y:= 6; x:= x + B;
        vec(2):= vec(2) * x;
        while (i < y) do begin
            vec(i):= vec(i) + B - 1;
            i:= i + 3;
        end;
        y:= y - 4;
    end;
    begin
        for x:= 1 to 7 do vec(x):= x;
        x:= 3; y:= B+5; D;
        if (x = 2) then begin
            vec(x):= vec(x) + 2;
            vec(x + 3):= vec(x) * 3;
        end;
        for x:= 1 to 7 do write(vec(x));
end.
