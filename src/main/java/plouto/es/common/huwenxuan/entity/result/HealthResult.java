package plouto.es.common.huwenxuan.entity.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class HealthResult {
    private String cluster_name;
    private String status;
    private boolean timed_out;
    private String number_of_nodes;
    private String number_of_data_nodes;
    private String active_primary_shards;
    private String active_shards;
    private String relocating_shards;
    private String initializing_shards;
    private String unassigned_shards;
    private String delayed_unassigned_shards;
    private String number_of_pending_tasks;
    private String number_of_in_flight_fetch;
    private String task_max_waiting_in_queue_millis;
}
