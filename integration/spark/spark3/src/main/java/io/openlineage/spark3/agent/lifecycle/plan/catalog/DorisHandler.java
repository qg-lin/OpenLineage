package io.openlineage.spark3.agent.lifecycle.plan.catalog;

import io.openlineage.client.utils.DatasetIdentifier;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.execution.datasources.v2.DataSourceV2Relation;

@Slf4j
public class DorisHandler implements RelationHandler {
    @Override
    public boolean hasClasses() {
        return true;
    }

    @Override
    public boolean isClass(DataSourceV2Relation relation) {
        return relation.table().getClass().getName().equals("org.apache.doris.spark.catalog.DorisTable");
    }

    @Override
    public DatasetIdentifier getDatasetIdentifier(DataSourceV2Relation relation) {
        String[] tableParts = relation.table().name().split("\\.");
        String name = tableParts[1];
        String namespace = tableParts[0];
        return new DatasetIdentifier(name, namespace);
    }

    @Override
    public String getName() {
        return "doris";
    }
}
