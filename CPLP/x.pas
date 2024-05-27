Procedure Main;
    var j, m, i: integer;
    Procedure Recibe (x:integer; y:integer);
    begin
        m:= m + 1 + y;
        x:=i + x + j;
        y:=m - 1;
        write (x, y, i, j, m);
    end;
    Procedure Dos;
        var m:integer;
    begin
        m:= 5;
        Recibe(i, j);
        write (i, j, m);
    end;
begin
    m:= 2;
    i:=1; j:=3;
    Dos;
    write (i, j, m);
end.
