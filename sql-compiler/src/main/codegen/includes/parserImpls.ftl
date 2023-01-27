SqlNode SqlCreateMaterializedView() :
{
    SqlParserPos pos;
    SqlIdentifier viewName;
    boolean existenceCheck = false;
    SqlSelect query;
}
{
    <CREATE> { pos = getPos(); }
    <MATERIALIZED> <VIEW>
    <#-- [] 代表里面的元素可能出现 -->
        [ <IF> <NOT> <EXISTS> { existenceCheck = true; } ]
    <#-- CompoundIdentifier() 为 Calcite 内置函数，可以解析类似 catalog.schema.tableName 这样的全路径表示形式 -->
    viewName = CompoundIdentifier()
    <AS>
    <#-- SqlSelect() 为 Calcite 内置函数，解析一个 select sql -->
    query = SqlSelect()
    {
        return new CreateMaterializedView(pos, viewName, existenceCheck, query);
    }
}